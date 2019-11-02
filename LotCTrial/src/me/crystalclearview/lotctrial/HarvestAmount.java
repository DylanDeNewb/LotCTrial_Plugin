package me.crystalclearview.lotctrial;

public enum HarvestAmount {


    //Enums used for receiving amounts of seeds and crop.

    stonewoodhoe(1,1),
    irongoldhoe(2,2),
    diamondhoe(3,3);

    private int crop;
    private int seed;

    HarvestAmount(int crop, int seed){

        this.crop = crop;
        this.seed = seed;

    }

    public int getCrop(){
        return crop;
    } //Method to get the amount of Crop.
    public int getSeed(){
        return seed;
    } //Method to get the amount of Seed.

}
