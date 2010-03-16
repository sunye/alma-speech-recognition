package fr.alma.asr.test;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.alma.asr.entities.Folder;
import fr.alma.asr.entities.Lesson;

/**
 * @author Cédric Krommenhoek
 */
public class FolderTest extends TestCase {

	private Folder dossier;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.dossier = new Folder("Dossier de test");
		this.dossier.addElements(new Folder("Sous dossier"));
		this.dossier.addElements(new Lesson("Cours"));
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public final void testIsFile() {
		assertFalse(this.dossier.isFile());
	}

	@Test
	public final void testSetElements() {
		Folder nouveauDossier = new Folder("Nouveau dossier");
		nouveauDossier.setElements(this.dossier.getElements());
		assertEquals(this.dossier.getElements(), nouveauDossier.getElements());
	}

	@Test
	public final void testAddElementIndex() {
		int nb = this.dossier.getElements().size();
		this.dossier.addElementIndex(new Folder("Autre dossier"), 0);
		assertEquals(nb + 1, this.dossier.getElements().size());
	}

	@Test
	public final void testModule() {
		assertFalse(this.dossier.isModule());
		this.dossier.setModule();
		assertTrue(this.dossier.isModule());
	}

	@Test
	public final void testGetDateCreation() {
		assertNotNull(this.dossier.getDateCreation());
	}

	@Test
	public final void testGetDateModification() {
		assertNotNull(this.dossier.getDateModification());
		Date date = this.dossier.getDateModification();
		this.dossier.setNom("Nouveau nom de dossier");
		assertNotSame(date, this.dossier.getDateModification());
	}

	@Test
	public final void testToString() {
		assertEquals(this.dossier.getNom(), this.dossier.toString());
	}

}
