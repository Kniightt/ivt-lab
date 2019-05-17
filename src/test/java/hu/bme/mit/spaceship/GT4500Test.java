package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTorpedo1;
  private TorpedoStore mockTorpedo2;

  @BeforeEach
  public void init(){
	mockTorpedo1 = mock(TorpedoStore.class);
	mockTorpedo2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTorpedo1, mockTorpedo2);
  }
  
    @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
	verify(mockTorpedo1, times(1)).fire(1);
    assertEquals(false, result);
  }

    @Test
  public void fireTorpedo_Single_Success_Primary(){
    // Arrange
	when(mockTorpedo1.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
	verify(mockTorpedo1, times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedo_Single_Success_Secondary(){
    // Arrange
	when(mockTorpedo1.isEmpty()).thenReturn(true);
	when(mockTorpedo2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
	verify(mockTorpedo2, times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	when(mockTorpedo1.fire(1)).thenReturn(true);
	when(mockTorpedo2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
	verify(mockTorpedo1, times(1)).fire(1);
	verify(mockTorpedo2, times(1)).fire(1);
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange
	
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
	verify(mockTorpedo1, times(1)).fire(1);
    assertEquals(false, result);
  }
}
