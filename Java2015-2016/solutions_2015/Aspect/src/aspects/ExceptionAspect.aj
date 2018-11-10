package aspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import code.ExceptionThrower;

public aspect ExceptionAspect {
	Logger logger = Logger.getLogger("ExceptionAspectLogger");
	
	{
//		logger.setUseParentHandlers(false);
		try {
			logger.addHandler(new FileHandler("aspect_exceptions.txt"));
		} catch (SecurityException | IOException e) {
			// not expected to happen
		}
	}

	after(Object o) throwing (Exception e): within(ExceptionThrower) && this(o) {
		logger.severe(e.toString());
//		logger.fine(objThis.toString());
	}
}
