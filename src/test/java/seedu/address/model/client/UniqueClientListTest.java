package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.testutil.ClientBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_clientNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        assertTrue(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientWithSameIdentityFieldsInList_returnsFalse() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(uniqueClientList.contains(editedAlice));
    }

    @Test
    public void add_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void add_duplicateClient_throwsDuplicatePersonException() {
        uniqueClientList.add(ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.add(ALICE));
    }

    @Test
    public void setClient_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(ALICE, null));
    }

    @Test
    public void setClient_targetClientNotInList_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.setClient(ALICE, ALICE));
    }

    @Test
    public void setClient_editedClientIsSameClient_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, ALICE);
        UniqueClientList expectedUniquePersonList = new UniqueClientList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasSameIdentity_success() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        uniqueClientList.setClient(ALICE, editedAlice);
        UniqueClientList expectedUniquePersonList = new UniqueClientList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasDifferentIdentity_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, BOB);
        UniqueClientList expectedUniquePersonList = new UniqueClientList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasNonUniqueIdentity_throwsDuplicateClientException() {
        uniqueClientList.add(ALICE);
        uniqueClientList.add(BOB);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClient(ALICE, BOB));
    }

    @Test
    public void remove_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_clientDoesNotExist_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.remove(ALICE));
    }

    @Test
    public void remove_existingClient_removesClient() {
        uniqueClientList.add(ALICE);
        uniqueClientList.remove(ALICE);
        UniqueClientList expectedUniquePersonList = new UniqueClientList();
        assertEquals(expectedUniquePersonList, uniqueClientList);
    }

    @Test
    public void setClients_nullUniqueClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((UniqueClientList) null));
    }

    @Test
    public void setClients_uniqueClientList_replacesOwnListWithProvidedUniqueClientList() {
        uniqueClientList.add(ALICE);
        UniqueClientList expectedUniquePersonList = new UniqueClientList();
        expectedUniquePersonList.add(BOB);
        uniqueClientList.setClients(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniqueClientList);
    }

    @Test
    public void setClients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setClients_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ALICE);
        List<Client> personList = Collections.singletonList(BOB);
        uniqueClientList.setClients(personList);
        UniqueClientList expectedUniquePersonList = new UniqueClientList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueClientList);
    }

    @Test
    public void setClients_listWithDuplicateClients_throwsDuplicateClientException() {
        List<Client> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClients(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }
}
