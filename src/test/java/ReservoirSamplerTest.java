import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.enginyoyen.sampler.ReservoirSampler;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Engin Yoeyen on 21/02/15.
 */
public class ReservoirSamplerTest {

    final String[]  samples = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    final Integer[] samplesNumeric = {0,1,2,3,4,5,6,7,8,9};
    final Integer testDataSize = 1000;
    final SimpleObject[] sampleObjects = generateTestData(testDataSize);


    @Test(expectedExceptions = ClassCastException.class)
    public void classCasting() {
        ReservoirSampler sampler = new ReservoirSampler(Integer.class, samplesNumeric.length);
        for(Integer i : samplesNumeric ){
            sampler.sample(i);
        }
        String[] result = (String[])sampler.getReservoirArray();
    }

    @Test
    public void minimumSampleSize() {
        ReservoirSampler sampler = new ReservoirSampler(String.class, samples.length);
        for(String str : samples ){
            sampler.sample(str);
        }
        Assert.assertEquals(sampler.getReservoirArray(), samples);
    }


    @Test
    public void countAndIndexCheck() {
        Integer sampleCount = 10;
        ReservoirSampler sampler = new ReservoirSampler(SimpleObject.class, sampleCount);
        for(SimpleObject obj : sampleObjects ){
            sampler.sample(obj);
        }
        SimpleObject[] result = (SimpleObject[])sampler.getReservoirArray();

        for (int i = 0; i < result.length; i++) {
           System.out.println(result[i]);
        }
        Assert.assertEquals(sampler.getCurrentIndex(), testDataSize);
        Assert.assertEquals(sampler.getSampleCount(), sampleCount);
    }
    
    
    
    @Test
    public void checkWhetherDataIsIntact() {
        Integer sampleCount = 10;
        ReservoirSampler sampler = new ReservoirSampler(String.class, sampleCount);

        List<String> testdata = new ArrayList<String>();
        BufferedReader br = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader("./src/test/resources/testdata.txt"));
            while ((currentLine = br.readLine()) != null) {
                sampler.sample(currentLine);
                testdata.add(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String[] result = (String[]) sampler.getReservoirArray();
        int totalNumberOfMatch = 0;
        for (String itemExpected : testdata) {
            for (String itemActual : result){
                if (itemExpected == itemActual) {
                    totalNumberOfMatch++;
                }
            }
        }
        
        if(totalNumberOfMatch != sampleCount){
            Assert.fail("Sampled data does not match with any original data");
        }
        
    }

    public SimpleObject[] generateTestData(Integer size) {
        SimpleObject[] array = new SimpleObject[size];
        for (int i = 0; i <  size; i++) {
            array[i] = new SimpleObject(UUID.randomUUID().toString(),i);
        }
        return array;
    }
    

}
