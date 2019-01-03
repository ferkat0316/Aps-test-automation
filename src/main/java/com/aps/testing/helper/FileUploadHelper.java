package com.aps.testing.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aps.testing.configuration.BasicConfiguration;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Component
public class FileUploadHelper {

	@Autowired
	BasicConfiguration basicConfig;

	private static final Logger LOGGER = Logger.getLogger(FileUploadHelper.class.getName());

	public String fileUpload(String command, String fileName, String nifiFolder)
			throws IOException, SftpException {
		String result = "";
		File file = (fileName.equals("")) ? null : new File("data/inputData/" + nifiFolder + "/" + fileName);
		try {
			Session session = null;
			JSch jsch = new JSch();
			int port = 22;
			
			if (basicConfig.getEnvName().equalsIgnoreCase("aws")) {
				jsch.addIdentity(basicConfig.getSecrets());
				JSch.setConfig("StrictHostKeyChecking", "no");
				session = jsch.getSession(basicConfig.getUser(), basicConfig.getServer(), port);
			} else {
				JSch.setConfig("StrictHostKeyChecking", "no");
				session = jsch.getSession(basicConfig.getUser(), basicConfig.getServer());
				session.setPort(port);
				session.setPassword(basicConfig.getSecrets());
			}
			session.connect();

			/**
			 * creates an sftp session to transfer the file to the root dir
			 */
			Channel channel1 = session.openChannel("sftp");
			channel1.connect();

			ChannelSftp c = (ChannelSftp) channel1;
			try {
				if (file != null)
					c.put(new FileInputStream(file), file.getName());

			} catch (IOException e) {
				LOGGER.info(e.getMessage());
			}
			c.exit();
			channel1.disconnect();

			/**
			 * creates an ssh session to run shell commands on the remote server
			 */
			Channel channel = session.openChannel("shell");
			LOGGER.info("shell channel connected....");
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = new PrintStream(ops, true);
			String stageFolder = "stage-files";
			String command0 = null;
			String command1 = null;
			String command2 = null;
			String command3 = null;
			String command4 = null;

			if (file != null) {
				command0 = "sudo su - " + basicConfig.getUser();
				command1 = "sudo mv " + fileName + " " + basicConfig.getPath() + stageFolder + "/";
				command2 = "sudo chown nifi:nifi " + basicConfig.getPath() + stageFolder + "/" + fileName;
				command3 = "sudo su - nifi";

				command4 = "cat " + basicConfig.getPath() + stageFolder + "/" + fileName + ">" + basicConfig.getPath()
						+ nifiFolder + "/" + fileName;

			}
			channel.connect();
			ps.println(command0);
			ps.println(command1);
			ps.println(command2);
			ps.println(command3);
			ps.println(command4);

			InputStream in = channel.getInputStream();
			byte[] home = new byte[1024];

			while (in.available() > 0) {
				int i = in.read(home, 0, 1024);
				if (i < 0)
					channel.disconnect();
			}

			if (channel.isClosed()) {
				LOGGER.info("exit-status: " + channel.getExitStatus());
			}

			insertWait();
			result = "File Transfered";

			channel.disconnect();
			session.disconnect();

		} catch (JSchException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void insertWait() {

		try {
			Thread.sleep(1000);
		} catch (Exception ee) {
			LOGGER.log(Level.INFO, "Thread Exception", ee.getMessage());
		}
	}

}
