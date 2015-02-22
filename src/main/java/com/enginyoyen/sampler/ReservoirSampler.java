package com.enginyoyen.sampler;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * *
 * * Reservoir sampler class for randomly choosing a sample of k 
 * * items from a list S containing n items, 
 * * where n is either a very large or unknown number.
 * * 
 * * Created by Engin Yoeyen on 21/02/15.
 * *
 */
public class ReservoirSampler<T>{

    private Random random = new Random();
  
    private Integer sampleCount = 0;
    
    private Integer currentIndex = 0;
    
    private  T[] reservoirArray;

    /**
     * * Constructor requires the class type for the array.
     * @param clazz Class type
     * @param sampleCount Sample count (l)
     */
    public ReservoirSampler(Class<T> clazz, int sampleCount){
        @SuppressWarnings("unchecked")
        final T[] array = (T[]) Array.newInstance(clazz, sampleCount);
        this.sampleCount = sampleCount;
        this.reservoirArray = array;
    }

    /**
     * * Samples the incoming data stream, first k items are populate into the reservoir
     * * without any calculation. It then replaces the elements in the reservoir 
     * * gradually with decreasing probability
     * @param item
     */
    public void sample(T item) {
        // Populate the reservoir array with first k items
        if(currentIndex < sampleCount) {
            reservoirArray[currentIndex] = item;
        }else{
            // replaces the elements in the reservoir array
            int randomInt = getRandomNumber(currentIndex);
            if (randomInt < sampleCount){
                reservoirArray[randomInt] = item;
            }
        }
        currentIndex++;
    }

    /**
     * * Returns the sample count(k)
     * @return
     */
    public Integer getSampleCount() {
        return sampleCount;
    }

    /**
     * * Returns the current index, which is equal to amount of data items that is processed (S) 
     * @return
     */
    public Integer getCurrentIndex() {
        return currentIndex;
    }

    /**
     * * Returns the sampled array
     * @return
     */
    public T[] getReservoirArray() {
        return reservoirArray;
    }

    /**
     * * Generates random number for internal use only
     * @param max
     * @return
     */
    private Integer getRandomNumber(Integer max) {
        return random.nextInt((max - 0) + 1) + 0;
    }

}
