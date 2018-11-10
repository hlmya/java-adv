package aspects;

import code.Airplane;

public aspect SaveGimliGlider {
	double around(Airplane plane): target(plane) && call(double Airplane.getMaxFlightDistance()) {
		return plane.getFuel() / Airplane.kmPerKg;
	}
}
