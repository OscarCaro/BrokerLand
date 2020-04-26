package controller;

import org.apache.commons.cli.*;

public class main {
    private static final int BOTSDEFAULTNUM = 50;
    private static int numbots = -1;
    private static Difficulty difficulty = null;

    public static void main(String[] args) {
        Options cmdLineOptions = buildOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(cmdLineOptions, args);
            parseHelpOption(line, cmdLineOptions);
            parseBotsOption(line);
            parseDiffOption(line);
            // if there are some remaining arguments, then something wrong is
            // provided in the command line!
            //
            String[] remaining = line.getArgs();
            if (remaining.length > 0) {
                String error = "Illegal arguments:";
                for (String o : remaining)
                    error += (" " + o);
                throw new ParseException(error);
            }
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        Game game = new Game(numbots, difficulty);
        game.run();

    }

    private static Options buildOptions() {
        Options cmdLineOptions = new Options();
        cmdLineOptions.addOption(Option.builder("b").longOpt("bots").hasArg().desc("Number of bots (default value is 50).").build());
        cmdLineOptions.addOption(
                Option.builder("d").longOpt("difficulty").hasArg().desc("Set difficulty, values are: Easy, Normal, Difficult, WorldTradeCenter.").build());
        cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());

        return cmdLineOptions;
    }

    private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
        if (line.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(main.class.getCanonicalName(), cmdLineOptions, true);
            System.exit(0);
        }
    }

    private static void parseBotsOption(CommandLine line) {
        if (line.hasOption("b")) {
            numbots = Integer.parseInt(line.getOptionValue("b"));
        } else {
            numbots = BOTSDEFAULTNUM;
        }
    }

    private static void parseDiffOption(CommandLine line) {
        if (line.hasOption("d")) {
            difficulty = Difficulty.parse(line.getOptionValue("d"));
        }
    }

}
