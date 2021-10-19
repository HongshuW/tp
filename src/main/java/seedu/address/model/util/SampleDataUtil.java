package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"), new PhoneNumber("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40")),
            new Client(new Name("Bernice Yu"), new PhoneNumber("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Client(new Name("Charlotte Oliveiro"), new PhoneNumber("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Client(new Name("David Li"), new PhoneNumber("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Client(new Name("Irfan Ibrahim"), new PhoneNumber("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35")),
            new Client(new Name("Roy Balakrishnan"), new PhoneNumber("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static Product[] getSampleProducts() {
        return new Product[] {
                new Product(new Name("IPhone 13"), new UnitPrice("800"), new Quantity("1000")),
                new Product(new Name("Air Pods Pro"), new UnitPrice("200"), new Quantity("1000")),
                new Product(new Name("Panadol"), new UnitPrice("8"), new Quantity("500")),
                new Product(new Name("Mask"), new UnitPrice("0"), new Quantity("10000"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        for (Product sampleProduct : getSampleProducts()) {
            sampleAb.addProduct(sampleProduct);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
