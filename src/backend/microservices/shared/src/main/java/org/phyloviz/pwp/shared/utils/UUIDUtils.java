package org.phyloviz.pwp.shared.utils;

import java.util.UUID;

/**
 * Utility class for UUIDs.
 */
public class UUIDUtils {
    private UUIDUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if a string is a valid UUID.
     *
     * @param uuid the string to check
     * @return true if the string is a valid UUID, false otherwise
     */
    public static boolean isValidUUID(String uuid) {
        if (uuid == null)
            return false;

        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException ex) {
            return false;
        }

        return true;
    }
}
