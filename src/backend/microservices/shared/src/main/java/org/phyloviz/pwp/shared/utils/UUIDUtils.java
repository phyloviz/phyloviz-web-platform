package org.phyloviz.pwp.shared.utils;

/**
 * Utility class for UUIDs.
 */
public class UUIDUtils {
    private UUIDUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidUUID(String uuid) {
        if (uuid == null)
            return false;

        try {
            java.util.UUID.fromString(uuid);
        } catch (IllegalArgumentException ex) {
            return false;
        }

        return true;
    }
}
