package SwedishRadioInfo

import org.junit.Test

/**
 * Created by mian on 2016-12-28.
 *
 */
class ParseSRTableauTest {


    @Test
    void testConstructor(){
        ParseSRTableau parseSR = new ParseSRTableau();

    }

    @Test
    void testParseSwedishRadioAPI() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau();

        parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");

        assert(true);

    }


    @Test
    void testCheckHowManyChannelsWasFound() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");


        assert(channelInfo.size() == 56);

    }

    @Test
    void testIfCertainChannelExist() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");
        boolean found = false;

        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getName().equals(new String("P2"))){
                found = true;
            }
        }
        assert(found);
    }

    @Test
    void testForNonExistingChannel() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");
        boolean found = false;

        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getName().equals(new String("P2s"))){
                found = true;
            }
        }
        assert(!found);
    }

    @Test
    void testParseChannelTableaul() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");

        parseSR.parseChannelTableau(channelInfo);

        println (channelInfo.get(0).getProgramInfo().size());

        for(ProgramInformation pinfo: channelInfo.get(0).getProgramInfo()){
        }
    }

    @Test
    void testParseTestToReadFromSRUrl() throws IOException{
        ParseSRTableau parseSR = new ParseSRTableau();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://api.sr.se/api/v2/channels?pagination=false");

        parseSR.parseChannelTableau(channelInfo);

        println (channelInfo.get(0).getProgramInfo().size());

        for(ProgramInformation pinfo: channelInfo.get(0).getProgramInfo()){
        }
    }
}
