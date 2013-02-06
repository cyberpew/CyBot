package me.cyberpew.CyBot.features;

import org.pircbotx.*;
import java.util.Random;

public class Randomizer {
        public static String Fapget() {
                String fapget;
                Random gen = new Random();
                int randomInt = gen.nextInt(6);
                switch(randomInt) {
                        case 0: fapget = " Hentai"; break;
                        case 1: fapget = " Nugget Porn"; break;
                        case 2: fapget = " Redheads"; break;
                        case 3: fapget = " Santa Claus Porn"; break;
                        case 4: fapget = " Gay Porn"; break;
                        case 5: fapget = " Goatse"; break;
                        //case 5: fapget = " Gay Porn"; break;
                        //case 6: fapget = " Gay Porn"; break;
                        //case 7: fapget = " Gay Porn"; break;
                        //case 8: fapget = " Gay Porn"; break;
                        //case 9: fapget = " Gay Porn"; break;
                        //case 10: fapget = " Gay Porn"; break;

                        // this will only be relayed if randomInt generated a number > 5 or < 0.
                        default: fapget = "error";
                        }
                return fapget;
        }
        public static String Slap() {
            String slap;
            Random gen = new Random();
            int randomInt = gen.nextInt(6);
            switch(randomInt) {
                    case 0: slap = " with a dead fish"; break;
                    case 1: slap = " with a prostitute's cunt rag"; break;
                    case 2: slap = " with a frying pan"; break;
                    case 3: slap = " with a null pointer exception"; break;
                    case 4: slap = " with CyBot's dick"; break;
                    case 5: slap = " "; break;
                    // this will only be relayed if randomInt generated a number > 5 or < 0.
                    default: slap = "error";
                    }
            return slap;
    }
        public static String Hit() {
            String hit;
            Random gen = new Random();
            int randomInt = gen.nextInt(5);
            switch(randomInt) {
                    case 0: hit = " with a brick"; break;
                    case 1: hit = " with a shovel"; break;
                    case 2: hit = " with his car"; break;
                    case 3: hit = " with his fist"; break;
                    case 4: hit = " with a train"; break;
                    // this will only be relayed if randomInt generated a number > 4 or < 0.
                    default: hit = "error";
                    }
            return hit;
    }
        public static String Drinks() {
            String drinks;
            Random gen = new Random();
            int randomInt = gen.nextInt(37);
            switch(randomInt) {
                    case 0: drinks = " a Air Force One"; break;
                    case 1: drinks = " a Alabama Slammer"; break;
                    case 2: drinks = " a Allies Cocktail"; break;
                    case 3: drinks = " a Aman Snow Leopard"; break;
                    case 4: drinks = " a Amaretto Sour"; break;
                    case 5: drinks = " a Amarula & Eve"; break;
                    case 6: drinks = " a Jim Beam Fruit Punch"; break;
                    case 7: drinks = " a John Collins"; break;
                    case 8: drinks = " a Johnny Weissmuller Cocktail"; break;
                    case 9: drinks = " a Jolly Rancher"; break;
                    case 10: drinks = " a Journalist"; break;
                    case 11: drinks = " a French Pearl Martini"; break;
                    case 12: drinks = " a French Sparkle"; break;
                    case 14: drinks = " a Fresh Squeeze"; break;
                    case 15: drinks = " a Friar Tuck"; break;
                    case 16: drinks = " a Frisco Sour"; break;
                    case 17: drinks = " a Orgasm"; break;
                    case 18: drinks = " a Oriental Cocktail"; break;
                    case 19: drinks = " a Oriental Passion"; break;
                    case 20: drinks = " a Oriental Express"; break;
                    case 21: drinks = " a Oaxaca Chakas"; break;
                    case 22: drinks = " a Obituary Cocktail"; break;
                    case 23: drinks = " a Hennessy"; break;
                    case 24: drinks = " a Beer"; break;
                    case 25: drinks = " a glass of Wine"; break;
                    case 26: drinks = " some Rum"; break;
                    case 27: drinks = " a glass of Vodka"; break;
                    case 28: drinks = " some Whisky"; break;
                    case 29: drinks = " some Tequila"; break;
                    case 30: drinks = " some Gin"; break;
                    case 31: drinks = " a Triple Sec"; break;
                    case 32: drinks = " some Peach Schnapps"; break;
                    case 33: drinks = " a Blue Curacao"; break;
                    case 34: drinks = " a Cognac"; break;
                    case 35: drinks = " some Bourbon"; break;
                    case 36: drinks = " some Peach Schnapps"; break;
                    // this will only be relayed if randomInt generated a number > 36 or < 0.
                    default: drinks = "error";
                    }
            return drinks;
    }
}