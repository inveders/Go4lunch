package com.inved.go4lunch;

import com.inved.go4lunch.utils.UnitConversion;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class UnitConversionTest {

    private UnitConversion unitConversion = Mockito.spy(new UnitConversion()); //Utiliser cette notation


    @Test
    public void should_ConvertInRadians_With_Degre() {

        //Given
        double degre = 1000;

        //When
        double radians = unitConversion.convertRad(degre);

        //Then

        Assert.assertEquals(17.453292519943293, radians);

    }


}