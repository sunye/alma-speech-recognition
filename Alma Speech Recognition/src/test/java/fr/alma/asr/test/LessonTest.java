package fr.alma.asr.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.alma.asr.dao.ElementDao;
import fr.alma.asr.dao.impl.AbstractDaoImpl;
import fr.alma.asr.dao.impl.ElementDaoImpl;
import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;

/**
 * @author Cédric Krommenhoek
 */
public class LessonTest extends TestCase {

	private ElementDao dao;
	private Lesson cours;
	private Folder dossier;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		AbstractDaoImpl.addSpecificProperty("hibernate.connection.url", "jdbc:h2:./test/db");
		this.dao = new ElementDaoImpl();
		this.dao.deleteAll();
		this.dossier = new Folder("Dossier de test");
		this.dossier.addElements(new Folder("Sous dossier"));
		this.cours = new Lesson("Cours");
		this.dossier.addElements(this.cours);
		this.dao.create(dossier);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
	}

	@Test
	public final void testDataEleve() {
		String data = "Des données...";
		assertNotNull(this.cours.getDataEleve());
		assertEquals("", this.cours.getDataEleve());
		this.cours.setDataEleve(this.cours.getDataEleve() + data + "\n");
		assertEquals("Des données...\n", this.cours.getDataEleve());
	}

	@Test
	public final void testDataProf() {
		String data = "Des données...";
		assertNotNull(this.cours.getDataProf());
		assertEquals("", this.cours.getDataProf());
		this.cours.setDataProf(this.cours.getDataProf() + data + "\n");
		assertEquals("Des données...\n", this.cours.getDataProf());
	}

	@Test
	public final void testHashCode() {
		Lesson cours1 = new Lesson(this.cours.getNom());
		this.dao.create(cours1);
		assertNotSame(this.cours.hashCode(), cours1.hashCode());
		cours1.setNom("Autre nom");		
		assertNotSame(this.cours.hashCode(), cours1.hashCode());
		
		Lesson cours2 = (Lesson) this.dao.find(this.cours.getId());		
		assertEquals(this.cours.hashCode(), cours2.hashCode());
		cours2.setNom("Nouveau nom");
		assertNotSame(this.cours.hashCode(), cours2.hashCode());		
	}

	@Test
	public final void testEqualsObject() {
		Lesson cours1 = new Lesson(this.cours.getNom());
		this.dao.create(cours1);
		assertNotSame(this.cours, cours1);
		cours1.setNom("Autre nom");
		
		Lesson cours2 = (Lesson) this.dao.find(this.cours.getId());
		assertEquals(this.cours, cours2);
		cours2.setNom("Nouveau nom");
		assertNotSame(this.cours, cours2);
	}
	
	@Test
	public final void testToString() {
		assertEquals(this.cours.getNom(), this.cours.toString());
	}

}
