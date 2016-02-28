package com.ada.timetracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ada.timetracker.model.ModelTests;
import com.ada.timetracker.model.WorkingBItManagerTest;
import com.ada.timetracker.model.WorkingBitTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ModelTests.class, 
})
public class AllTests {

}
