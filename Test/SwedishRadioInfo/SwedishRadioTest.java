package SwedishRadioInfo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mian on 2016-12-30.
 */
public class SwedishRadioTest {
    @Test
    public void update() throws Exception {

    }

    @Test
    public void retrieveChannelTableau() throws Exception {

    }

    @Test
    public void getChannelByCategory() throws Exception {

        SwedishRadio sr = new SwedishRadio("http://api.sr.se/api/v2/channels?pagination=false");

        Map<String, ArrayList<String>> category = sr.getChannelByCategory();

        boolean found = false;

        for(Map.Entry<String, ArrayList<String>> entry : category.entrySet()){
            String k = entry.getKey();
            List<String> v= entry.getValue();
            for(String program : v){
                System.out.println(k + "   " + program);
                if(k.equals("Rikskanal") && program.equals("P1")){
                   found = true;
                }
            }
        }
        assertTrue(found);
    }

    @Test
    public void getChannelByCategoryLookingForInvalidMatch() throws Exception {

        SwedishRadio sr = new SwedishRadio("http://api.sr.se/api/v2/channels?pagination=false");

        Map<String, ArrayList<String>> category = sr.getChannelByCategory();

        boolean found = false;

        for(Map.Entry<String, ArrayList<String>> entry : category.entrySet()){
            String k = entry.getKey();
            List<String> v= entry.getValue();
            System.out.printf(k + "  ");
            for(String program : v){
                System.out.printf(program);
                if(k.equals("Rikskanal") && program.equals("P1 Västerbotten")){
                    found = true;
                }
            }
            System.out.println();
        }
        assertFalse(found);
    }

}