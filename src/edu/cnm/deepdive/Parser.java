package edu.cnm.deepdive;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser {

  private Options options;

  public Parser() {
    options = new Options();

    Option option = Option.builder("l").longOpt("length").hasArg(true).argName("words")
        .numberOfArgs(1).optionalArg(false).required(false).type(Number.class)
        .desc("Number of words in passphrase").build();
    options.addOption(option);

    option = Option.builder("?").longOpt("help").hasArg(false).required(false).desc("display usage")
        .build();
    options.addOption(option);

    option = Option.builder("c").longOpt("command").hasArg(true).argName("operation")
        .numberOfArgs(1).optionalArg(false).required(false).type(String.class)
        .desc("Operation to perform").build();
    options.addOption(option);

    option = Option.builder("f").longOpt("for").hasArg(true).argName("identifier").numberOfArgs(1)
        .optionalArg(false).required(false).type(String.class)
        .desc("Unique identifier for credentials").build();
    options.addOption(option);

    option = Option.builder("u").longOpt("user").hasArg(true).argName("username").numberOfArgs(1)
        .optionalArg(false).required(false).type(String.class)
        .desc("Username or ID for credentials").build();
    options.addOption(option);

    option = Option.builder("p").longOpt("pass").hasArg(true).argName("password").numberOfArgs(1)
        .optionalArg(false).required(false).type(String.class)
        .desc("Password for credentials").build();
    options.addOption(option);

    option = Option.builder("g").longOpt("generate").hasArg(false).required(false)
        .desc("Generate a random passphrase").build();
    options.addOption(option);

    option = Option.builder("i").longOpt("info").hasArg(true).argName("additional info")
        .numberOfArgs(1).optionalArg(false).required(false).type(String.class)
        .desc(
            "Freeform info (if spaces are included, entire value must be surrounded by double quotes)")
        .build();
    options.addOption(option);

    option = Option.builder("t").longOpt("tags").hasArg().numberOfArgs(Option.UNLIMITED_VALUES).valueSeparator(' ').argName("category tags")
        .optionalArg(false).required(false).type(String.class)
        .desc("Space-delimited list of categories").build();
    options.addOption(option);
  }

  public Map<String, Object> parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);
    if (cmd.hasOption("?")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("java edu.cnm.deedive.Main [options]", options);
      return null;
    }
    HashMap<String, Object> optionsSpecified = new HashMap<>();
    for (Option option : cmd.getOptions()) {
      Object value = cmd.getParsedOptionValue(option.getOpt());
      optionsSpecified.put(option.getOpt(), value);
    }
    validate(optionsSpecified);
    return optionsSpecified;
  }

  private void validate(Map<String, Object> options) {
    // TODO validate method
  }

  public static class InvalidOptionCombination extends ParseException {

    public InvalidOptionCombination(String message) {
      super(message);
    }
  }

}
