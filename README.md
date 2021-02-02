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
1. Build your own GeoMap of your secnario.
1. Update the GeoMap data using SQL-like queries.

<br>

### How to build your own GeoMap of your scenario
1. Make classes of dimensions that extend *DimVar*
    ```
    something
    ```
    1. Implement `checkUpdateValid(int diff)` method
    2. Implement `updateValueOfDim(int diff)` method

1. Make a user-defined SoS class that extends SoS
    ```Java
    public class ExampleSoSType extends SoS
    ```
    1. Implement `initSoSModel()`
        * Declare and instantiate user-defined entities
        ```Java
        ExampleObjectType obj01 = new ExampleObjectType(this, "OBJ_01_ID", "OBJ_01_NAME");
        ```
        * Add the entities into the SoS
        ```Java
        addMemberEntity(obj01);
        ```

1. Make a user-defined map class that extends SoSMap
    ```Java
    public class MapType_04_Cuboid extends SoSMap
    ```
1. Declare and initialize dimension variables (*DimVar*s) with their domains (DimVarDomain) in the user-defined map class
    adsfasdf

    1. Implement `initMapDimensions()` method
        * Instantiate DimVarDomains
            ```Java
            DimVarDomain xPosDomain = new DimVarDomain(EnumDomainType.VALUE_RANGE_DISCRETE, 0, 2);
            ```
            ```Java
            DimVarDomain floorDomain = new DimVarDomain(EnumDomainType.ENUMERATION, new ArrayList<String>(Arrays.asList("FLOOR_1", "FLOOR_2")));
            ```
        * Make *DimVar* objects based on your own DimVar
            ```Java
            MapCoordinateDimType xPosDimVar = new MapCoordinateDimType( "xPosVar", "xPosVar", "Int", "0", "0", xPosDomain);
            ```
        * Add the *DimVar*s into the map
            ```Java
            addDimVar(xPosDimVar);
            ```
    1. Implement `initMapInformation()` method
        * Instantiate Data Var Domains
        * Make DataVar objects with the DataVarDomains defined
        * Add the DataVars into the map
            ```Java
            addDataVar(isWallVar);
            ```

1. Instantiate the user-defined map class at your SoS class file (`ExampleSoSType.java`) by implementing `initMap()` method of your SoS class
    ```Java
    MapType_04_Cuboid mapObject04Cuboid = new MapType_04_Cuboid("SOSMAP04",
                "EXAMPLE_SOS_MAP_04", "Map_Initialization_Type04_Cuboid.txt");
    ```

    Here, Map_Initialization_Type04_Cuboid.txt is a file for the map initialization. See [How to update map data](#how-to-update-map-data) section below.

1. Instantiate the user-defined SoS class at `Main.java`
    ```Java
    ExampleSoSType sos = new ExampleSoSType("SOS01", "EXAMPLE_SOS", "", cuboidMap , new ArrayList<Entity>());
    ```

<br>

### How to update map data
You need to make a text file that stores the formally defined geographical data.
The exemple of it is at the `ExampleSoSMapInit.txt`, and `ExampleSoSMapInit_pool.txt`.

```SQL
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
