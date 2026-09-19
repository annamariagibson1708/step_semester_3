package step_semester_3.access_modifier.practice;

public class AccessLinterExtension {

    /**
     * Classifies access using standard Java encapsulation rules across all 5 contexts.
     * Incorporates the compile-time type access nuance for protected fields in subclasses.
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
                // Protected items are available to same class, same package, and 
                // specifically to a subclass when accessed via its own type context.
                if ("SAME_CLASS".equals(accessorContext) || 
                    "SAME_PACKAGE".equals(accessorContext) || 
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                // It is explicitly DENIED when accessed via the Parent reference type across packages
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    /**
     * Converts underscore-separated codes into Title-Cased sentences.
     * Example: "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE" -> "Subclass Different Package Parent Type"
     */
    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        // Split tokens around underscores
        String[] words = accessorContext.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (!word.isEmpty()) {
                // Capitalize the first letter of the token
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1));
                
                // Add a space between words except after the last token
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }
}
