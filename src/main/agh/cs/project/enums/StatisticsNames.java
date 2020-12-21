package agh.cs.project.enums;

public enum StatisticsNames {
    DAY,
    NUMBER_OF_ANIMALS,
    NUMBER_OF_PLANTS,
    DOMINANT_GEN,
    AVERAGE_ENERGY,
    AVERAGE_LENGTH_OF_LIFE,
    AVERAGE_CHILDREN_NUMBER;

    public String toString(){
        switch(this){
            case DAY:
                return "day";
            case NUMBER_OF_ANIMALS:
                return "number of animals";
            case NUMBER_OF_PLANTS:
                return "number of plants";
            case DOMINANT_GEN:
                return "dominant gen";
            case AVERAGE_ENERGY:
                return "average energy";
            case AVERAGE_LENGTH_OF_LIFE:
                return "average length of life";
            case AVERAGE_CHILDREN_NUMBER:
                return "average children number";
        }
        return null;
    }
}
