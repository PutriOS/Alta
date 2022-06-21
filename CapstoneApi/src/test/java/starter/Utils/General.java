package starter.Utils;

import java.util.Random;

public class General {
    Random rand = new Random();

    public String randomUsername(){
        return "aisyah" + + rand.nextInt(1000); //mekanisme random bound 10.000.000 millisecond dari sekarang
    }
}
