# GeoMapBuilder-for-SoS
GeoMapBuilder-for-SoS is a constructor of geographical environment of system of systems (SoS) based on the *Meta-Model for System-of-Systems (M2SoS)*<sup id="a1">[1](#f1)</sup>.

This repository shows the overall process of building the geographical environment of SoS, and provides the simple exemples.

<br>

## Getting Started
### Prerequisites
```
- Java 15.1 
```

<br>

### How to use
1. Download the source code.
2. Edit the `Main.java` and `scenario` files based on your scenario.
3. Provides the formally defined geographical data as a text file.
4. Edit the `Main.java` to match with the scenario, and the map data file.

<br>

### Scenario Files
Scenario files are composed with four types of files
```
ExampleSoSType: SoS
ExampleObjectType: CS
ExampleSoSMap: SoSMap
MapCoordinateDimType / MapFloorDimType: DimVar
```

<br>

### Formally Defined Geographical Data
You need to make a text file that stores the formally defined geographical data.
The exemple of it is at the `ExampleSoSMapInit.txt`, and `ExampleSoSMapInit_pool.txt`.

```
SET(dustLevelVar=3);
SET(isWallVar=0, isChargingStationVar=1)WHERE(ALL);
SET(isWallVar=3)WHERE(xPosVar==1 && yPosVar==1);
```
The syntax is combines with `SET`, and `WHERE` for a single line.
`SET` shows the geographical data, and `WHERE` shows the location of the data.
If `WHERE` does not exist, GeoMapBuilder sets the data to whole possible location in the map.

<br>

## Reference
<!--
To find more information on GeoMapBuilder-for-SoS,
-->

---
<b id="f1"><sup>1</sup></b> Y.-M. Baek, J. Song, Y.-J. Shin, S. Park, and D.-H. Bae, “A meta-model for representing system-of-systems ontologies,” in 2018 IEEE/ACM 6th International Workshop on Software Engineering for Systems-of-Systems(SESoS). IEEE, 2018, pp. 1–7. [↩](#a1)<br>
