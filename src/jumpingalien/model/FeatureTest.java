package jumpingalien.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeatureTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void numberTest() {
		assertEquals(Feature.air.getNumber(), 0);
		assertEquals(Feature.ground.getNumber(), 1);
		assertEquals(Feature.water.getNumber(), 2);
		assertEquals(Feature.magma.getNumber(), 3);
	}

}