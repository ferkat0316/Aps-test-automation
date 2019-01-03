package com.aps.testing.cucumber.files;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = { "com.aps.testing.commons" }, plugin = { "html:target/cucumber-html-report",
		"json:target/cucumber.json", "pretty:target/cucumber-pretty.txt", "usage:target/cucumber-usage.json",
		"junit:target/cucumber-results.xml" }, features = {
				"src/main/java/com.aps.testing.features/" }, tags = "@entityResolutionZentityMatching")
public class CucumberRunner {

}
