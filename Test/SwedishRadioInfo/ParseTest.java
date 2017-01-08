package SwedishRadioInfo;

import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mian on 2017-01-06.
 */
public class ParseTest {


    @Test
    public void testConstructor(){
        XMLParseChannels parseSR = new XMLParseChannels();

    }

    @Test
    public void testParseSwedishRadioAPI() throws IOException {
        XMLParseChannels parseSR = new XMLParseChannels();

        parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");

        assert(true);

    }


    @Test
    public  void testCheckHowManyChannelsWasFound() throws IOException{
        XMLParseChannels parseSR = new XMLParseChannels();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");


        assert(channelInfo.size() == 56);

    }

    @Test
    public  void testIfCertainChannelExist() throws IOException{
        XMLParseChannels parseSR = new XMLParseChannels();
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
    public   void testForNonExistingChannel() throws IOException{
        XMLParseChannels parseSR = new XMLParseChannels();
        List<ChannelInformation> channelInfo;
        channelInfo = parseSR.parseChannels("http://www8.cs.umu.se/~mian/srchannels");
        boolean found = false;

        for(ChannelInformation cInfo: channelInfo){
            if(cInfo.getName().equals("P2s")){
                found = true;
            }
        }
        assert(!found);
    }

    @Test
    public  void testCheckProgramTimeInterval() throws IOException{
        XMLParseChannels parseSR = new XMLParseChannels();
        List<ChannelInformation> channelInfo;
        XMLParseTableau tableauPaser = new XMLParseTableau();

        channelInfo = parseSR.parseChannels(
                "http://api.sr.se/api/v2/channels?pagination=false");

        channelInfo.get(0).setProgramInfo(tableauPaser.
                parseChannelTableau(channelInfo.get(0).getSchedule()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -12);
        Date twelveHoursBefore = cal.getTime();

        cal.add(Calendar.HOUR_OF_DAY, +24);
        Date twelveHoursAfter = cal.getTime();
        boolean ok = true;


        for(ProgramInformation p : channelInfo.get(0).getProgramInfo()){
            if(!(p.getEpisodeDate_Start().after(twelveHoursBefore) &&
                    p.getEpisodeDate_Start().before(twelveHoursAfter))){
                ok=false;
            }
        }

        Assert.assertTrue(ok);

    }

    @Test
    public  void testTestInvalidTimeIntervall() throws IOException{
        XMLParseChannels parseSR = new XMLParseChannels();
        List<ChannelInformation> channelInfo;
        XMLParseTableau tableauPaser = new XMLParseTableau();
        channelInfo = parseSR.parseChannels(
                "http://api.sr.se/api/v2/channels?pagination=false");

        channelInfo.get(0).setProgramInfo(tableauPaser.
                parseChannelTableau(channelInfo.get(0).getSchedule()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -11);
        Date twelveHoursBefore = cal.getTime();

        cal.add(Calendar.HOUR_OF_DAY, +22);
        Date twelveHoursAfter = cal.getTime();
        boolean ok = true;


        for(ProgramInformation p : channelInfo.get(0).getProgramInfo()){
            if(!(p.getEpisodeDate_Start().after(twelveHoursBefore) &&
                    p.getEpisodeDate_Start().before(twelveHoursAfter))){
                ok=false;
            }
        }

        Assert.assertFalse(ok);

    }



}

