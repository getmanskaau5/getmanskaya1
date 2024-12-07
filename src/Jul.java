
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

    public class Jul {
        public static void main(String[] args) {

//        File text = new File("src/text.txt");
//        try (PrintWriter printWriter = new PrintWriter(text)){
//            printWriter.println("Люблю грозу в начале мая,\n" +
//                   "Когда весенний, первый гром,\n" +
//                   "Как бы резвяся и играя,\n" +
//                    "Грохочет в небе голубом.\n" +
//                    "Гремят раскаты молодые,\n" +
//                    "Вот дождик брызнул, пыль летит,\n" +
//                    "Повисли перлы дождевые,\n" +
//                    "И солнце нити золотит.\n" +
//                    "С горы бежит поток проворный,\n" +
//                    "В лесу не молкнет птичий гам,\n" +
//                    "И гам лесной, и шум нагорный –\n" +
//                    "Все вторит весело громам.\n" +
//                    "Ты скажешь: ветреная Геба,\n" +
//                    "Кормя Зевесова орла,\n" +
//                    "Громокипящий кубок с неба,\n" +
//                    "Смеясь, на землю пролила.");
//        }catch (FileNotFoundException fileNotFoundException){
//           fileNotFoundException.printStackTrace();
//
//        }


            File text2 = new File("src/text2.txt");
            try (PrintWriter printWriter = new PrintWriter(text2)){
                printWriter.println();
            }catch (FileNotFoundException fileNotFoundException){
                fileNotFoundException.printStackTrace();

            }

        }
    }


