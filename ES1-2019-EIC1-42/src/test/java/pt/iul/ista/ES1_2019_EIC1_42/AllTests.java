package pt.iul.ista.ES1_2019_EIC1_42;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pt.iul.ista.ES1_2019_EIC1_42.gui.Nova_RegraTest;

@RunWith(Suite.class)
@SuiteClasses({
	AppTest.class,
	Comparador_de_QualidadeTest.class,
	DataModelTest.class,
	ExcelDataTest.class,
	MetodoTest.class,
	RegraTest.class,
	Nova_RegraTest.class,	
})

public class AllTests {
	
}


