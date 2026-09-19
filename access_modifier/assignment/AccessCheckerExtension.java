package step_semester_3.access_modifier.assignment;

public class AccessCheckerExtension {

    /**
     * Classifies field visibility using standard Java encapsulation laws across 5 contexts.
     * Accurately distinguishes between compile-time variable reference types for cross-package subclasses.
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "protected":
                // Protected items are available in the same class, same package, 
                // and to cross-package subclasses ONLY via their own type reference.
                if ("SAME_CLASS".equals(accessorContext) || 
                    "SAME_PACKAGE".equals(accessorContext) || 
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                // Denied if accessed via the Parent reference type across packages
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    /**
     * Converts an underscore-separated code string into a human-readable title-cased sentence.
     * Example: "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE" -> "Subclass Different Package Own Type"
     */
    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        // Split words around underscores
        String[] words = accessorContext.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (!word.isEmpty()) {
                // Capitalize the first letter of each word token
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1));
                
                // Add trailing space between words (but not after the final word)
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }
}
