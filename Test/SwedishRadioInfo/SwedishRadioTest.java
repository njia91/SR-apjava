package SwedishRadioInfo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mian on 2016-12-30.
 */
public class SwedishRadioTest {
    @Test
    public void update() throws Exception {
        SwedishRadio sr = new SwedishRadio("http://api.sr.se/api/v2/channels?pagination=false");

        Map<String, ArrayList<String>> category = sr.getChannelByCategory();
        
        

        category.forEach((k,v) -> {
            System.out.println(k + "  " + v);
        });

    }

    @Test
    public void retrieveChannelTableau() throws Exception {

    }

    @Test
    public void getChannelByCategory() throws Exception {

    }

}