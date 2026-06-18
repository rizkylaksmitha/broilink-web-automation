package runner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = "cucumber.glue",
        value = "stepDef"
)
@ConfigurationParameter(
        key = "cucumber.plugin",
        value = "pretty, html:target/cucumber-report.html"
)
//@ConfigurationParameter(
//        key = "cucumber.filter.tags",
//        value = "@Focus"
//)
public class TestRunner {
}