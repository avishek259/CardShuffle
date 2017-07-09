This way we can get the input from user about the algorithm to be used at run time .

Add JVM arguments with -DargumentName i.e.

-DargumentName="value1"

Then in spring application, we can retrieve the value by doing:

@Value("${argumentName}")
private String myVariable


Another way is  :


You can run your app like this:

$ java -jar app.jar --someProperty=123

In application call:

import org.springframework.core.env.Environment;
@Autowired
private Environment env;

String someProperty= env.getProperty("someProperty");