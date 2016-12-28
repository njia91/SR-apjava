package SwedishRadioInfo

import org.junit.Test

/**
 * Created by mian on 2016-12-28.
 *
 */
class ParseSRTableauTest {


    @Test
    void testConstructor(){
        ParseSRTableau parseSR = new ParseSRTableau("http://api.sr.se/api/v2/channels");

    }

    @Test
    void testParseSwedishRadioAPI() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau("http://api.sr.se/api/v2/channels");

        parseSR.parseChannels();

        assert(true);

    }
}
