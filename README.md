#Reservoir Sampler [![Build Status][travis-ci_status_img]][travis-ci_status]
======
Reservoir sampling algorithm for choosing a random sample from a list where length of the list is either a very large or unknown number.

For more details, please refer to:
[http://en.wikipedia.org/wiki/Reservoir_sampling](http://en.wikipedia.org/wiki/Reservoir_sampling)


#Usage
Sampler uses array to hold the sampled data.
Because incoming data types can vary and depends on needs, and most likely it won't be a simple string,
sampler uses formal type parameters to abstract types. Therefore, when you are creating a new instance, 
you must give two parameters to constructor, first the type(e.g. String, Integer, Long or Custom) and sample count(k).


```
ReservoirSampler sampler = new ReservoirSampler(String.class, 10);

```
After initialization you can add a single data item by calling sample method:

```
sampler.sample("Data to be randomly selected");

```

Data items are added not in batches but as a single item, meaning it is your responsibility to deliver the data that is need it.
This abstraction allows very large data sets to be consumed without any problem as a stream of data, without whole data being present in the memory.

After operation is completed, you can fetch the sampled items as follow;

```
String[] result = (String[])sampler.getReservoirArray();

```

Data type that you cast, must be the same data type that you have initialized ReservoirSampler.

For more detailed examples, please refer to the tests.


#License
MIT License


[travis-ci_status_img]: https://travis-ci.org/enginyoyen/reservoir-sampler.svg?branch=master
[travis-ci_status]: https://travis-ci.org/enginyoyen/reservoir-sampler