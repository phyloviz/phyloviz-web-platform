package org.phyloviz.pwp.compute.utils;

/**
 * Utility class for UUIDs.
 */
public class UUIDUtils {
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