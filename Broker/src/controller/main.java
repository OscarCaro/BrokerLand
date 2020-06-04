package controller;

import controller.Difficulties.CustomDifficulty;
import controller.Difficulties.PremadeDifficulty;
import model.players.botBuild.BotBuildDirector;
import org.apache.commons.cli.*;

public class main {
    private static int numbots = -1;
    private static PremadeDifficulty premadeDifficulty = null;
    private static int msNum = 5;
    private static double dumbassRatio;
    private static double aggressiveRatio;
    private static double greedyRatio;
    private static double knowledgeableRatio;
    private static double randomRatio;
    private static double adaptability;
    private static int startAssets;
    private static int minAssets;
    private static double marketVolatilityRatio;

    //custom difficulty usage example
    //-c -1 0.1 -2 0.2 -3 0.3 -4 0.4 -5 0.5 -a 0.7 -s 3 -m 2 -b 30 -v 0.3
    public static void main(String[] args) {
        Options cmdLineOptions = buildOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(cmdLineOptions, args);
            CustomDifficulty c = parseCustomOption(line, cmdLineOptions);
            parseHelpOption(line, cmdLineOptions);
            parseDiffOption(line, cmdLineOptions);
            String[] remaining = line.getArgs();
            if (remaining.length > 0) {
                StringBuilder error = new StringBuilder("Illegal arguments:");
                for (String o : remaining)
                    error.append(" ").append(o);
                throw new ParseException(error.toString());
            }
            Game game = null;
            if (c != null) {
                try {
                    game = new Game(c);
                } catch (Exception e) {
                    System.err.println(e.getLocalizedMessage());
                    System.err.println("Problem with custom difficulty inputs.");
                    System.exit(1);
                }
            } else {
                game = new Game(premadeDifficulty);
            }
            game.run();
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }


    }

    private static Options buildOptions() {
        Options cmdLineOptions = new Options();
        cmdLineOptions.addOption(Option.builder("b").longOpt("bots").hasArg().desc("Number of bots that will accompany you.").build());
        cmdLineOptions.addOption(Option.builder("d").longOpt("premadeDifficulty").hasArg().desc("Set premadeDifficulty, values are: Easy, Normal, Difficult, WorldTradeCenter.").build());
        cmdLineOptions.addOption(Option.builder("c").longOpt("custom").desc("Custom play mode, to play this you will need to input options 1, 2, 3, 4, 5, a, s, m, b").build());
        cmdLineOptions.addOption(Option.builder("1").longOpt("type1").hasArg().desc("Ratio between 0 and 1 of AGGRESSIVE broker bots (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("2").longOpt("type2").hasArg().desc("Ratio between 0 and 1 of DUMBASS broker bots (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("3").longOpt("type3").hasArg().desc("Ratio between 0 and 1 of GREEDY broker bots (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("4").longOpt("type4").hasArg().desc("Ratio between 0 and 1 of KNOWLEDGEABLE broker bots (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("5").longOpt("type5").hasArg().desc("Ratio between 0 and 1 of RANDOM broker bots (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("v").longOpt("volatility").hasArg().desc("Ratio between 0 and 1 of market volatility (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("a").longOpt("adaptability").hasArg().desc("Adaptability to adversity ratio of the bots (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("s").longOpt("startingNum").hasArg().desc("Starting number of assets on the market (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("m").longOpt("minNum").hasArg().desc("Minimum number of assets on the market (Only needed if -c is enabled).").build());
        cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());
        return cmdLineOptions;
    }

    private static CustomDifficulty parseCustomOption(CommandLine line, Options cmdLineOptions) {
        if (line.hasOption("c")) {
            boolean aux = true;
            for (int i = 1; i <= msNum; i++) {
                if (!line.hasOption("" + i)) {
                    aux = false;
                }
            }
            if (aux && line.hasOption("a") && line.hasOption("s") && line.hasOption("m")) {
                parseBotsOption(line);
                parseTypesOption(line);
                parseAdaptabilityOption(line);
                parseMinNumOption(line);
                parseStartingNumOption(line);
                parseVolatilityOption(line);
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(main.class.getCanonicalName(), cmdLineOptions, true);
                System.exit(0);
            }
            return new CustomDifficulty(dumbassRatio, aggressiveRatio, greedyRatio, knowledgeableRatio, randomRatio, adaptability, minAssets, startAssets, numbots, marketVolatilityRatio);
        }
        return null;
    }

    private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
        if (line.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(main.class.getCanonicalName(), cmdLineOptions, true);
            System.exit(0);
        }
    }

    private static void parseBotsOption(CommandLine line) {
        numbots = Math.max(1, Integer.parseInt(line.getOptionValue("b")));
    }

    private static void parseAdaptabilityOption(CommandLine line) {
        marketVolatilityRatio = Math.max(Math.min(0.0, Double.parseDouble(line.getOptionValue("v"))), 1.0);
    }

    private static void parseVolatilityOption(CommandLine line) {
        adaptability = Math.max(Math.min(0.0, Double.parseDouble(line.getOptionValue("a"))), 1.0);
    }

    private static void parseTypesOption(CommandLine line) { //these are okay to leave unbounded
        aggressiveRatio = Double.parseDouble(line.getOptionValue("1"));
        dumbassRatio = Double.parseDouble(line.getOptionValue("2"));
        greedyRatio = Double.parseDouble(line.getOptionValue("3"));
        knowledgeableRatio = Double.parseDouble(line.getOptionValue("4"));
        randomRatio = Double.parseDouble(line.getOptionValue("5"));
    }

    private static void parseStartingNumOption(CommandLine line) {
        startAssets = Math.max(Integer.parseInt(line.getOptionValue("s")), 1);
    }

    private static void parseMinNumOption(CommandLine line) {
        minAssets = Math.max(Integer.parseInt(line.getOptionValue("m")), 1);

    }

    private static void parseDiffOption(CommandLine line, Options cmdLineOptions) {
        if (line.hasOption("d")) {
            premadeDifficulty = PremadeDifficulty.parse(line.getOptionValue("d"));
            if (premadeDifficulty == null) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(main.class.getCanonicalName(), cmdLineOptions, true);
                System.exit(0);
            }
        } else {
            premadeDifficulty = BotBuildDirector.DEFAULT_PREMADE_DIFFICULTY;
        }
    }

}
