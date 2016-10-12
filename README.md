zip-tools
==========
ZIP related libraries based on commons-compress lib.

### in-memory-zip
[in-memory-zip](../../tree/master/in-memory-zip)

Contains implementation of in memory ZIP archive (`InMemoryZip` class) based on ZipFile code. Uses commons-compress as a dependency.

### Modified commons-compress
[commons-compress](../../tree/master/commons-compress)

Modified commons-compress library with added `InMemoryZip` class. 
`ZipArchive` class was created to be a common base for `ZipFile` and `InMemoryZip` classes.
To achieve this `RandomAccessDataInput` interface was created that can be common to `RandomAccessFile` and `DataInputStream`.