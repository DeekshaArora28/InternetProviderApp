package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ClientOrder implements Serializable {
    private int clNumber;
    private String internetType;
    private int nbmonths;

    public ClientOrder(int clNumber, String internetType, int nbmonths) {
        this.clNumber = clNumber;
        this.internetType = internetType;
        this.nbmonths = nbmonths;
    }

    public int getClNumber() {
        return clNumber;
    }

    public void setClNumber(int clNumber) {
        this.clNumber = clNumber;
    }

    public String getInternetType() {
        return internetType;
    }

    public void setInternetType(String internetType) {
        this.internetType = internetType;
    }

    public int getNbmonths() {
        return nbmonths;
    }

    public void setNbmonths(int nbmonths) {
        this.nbmonths = nbmonths;
    }

    @NonNull
    @Override
    public String toString() {
        return "Client number: "+clNumber+ "Internet Provider:" +internetType+ "Nb. Months:" +nbmonths+ "Amount:" +getAmount();
    }

    public float getAmount() {
        float amount = 0;

        if(internetType.equals("Bell")|| nbmonths <= 1 )
            amount=nbmonths*60.0f;

        else
        if(internetType.equals("Bell") || nbmonths == 12)
            amount = 600.0f;
        else
        if(internetType.equals("Videotron") || nbmonths <=1)
            amount = nbmonths*70.0f;
        else
            if(internetType.equals("Videotron") || nbmonths == 6)
                amount = 350.0f;

        else
            if(internetType.equals("Videotron") || nbmonths == 12)
                amount = amount - (nbmonths*70.0f*30)/100;

        else
            if(internetType.equals("aCanac") || nbmonths == 1){
                amount = 45.0f;
            }
        else
            if(internetType.equals("aCanac") || nbmonths == 12)
                amount = 11 * 45.0f;



        return amount;
    }
}
