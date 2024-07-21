package TYPES;


public class TYPE_LIST 
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public TYPE head;
    public TYPE_LIST tail;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public TYPE_LIST(TYPE head, TYPE_LIST tail) 
	{
        this.head = head;
        this.tail = tail;
    }

    public static boolean sameListValuesAndOrder(TYPE_LIST caller, TYPE_LIST originalArgs) {
        // If both lists are null, they are equal
        if (caller == null && originalArgs == null) {
            return true;
        }
    
        // If only one list is null, they are not equal
        if (caller == null || originalArgs == null) {
            return false;
        }
    
        // Traverse both lists simultaneously
        while (caller != null && originalArgs != null) {
            // Check if types are equal or if one is an ancestor of the other
            if (!areTypesCompatible(caller.head, originalArgs.head)) {
                return false;
            }
            caller = caller.tail;
            originalArgs = originalArgs.tail;
        }
    
        // If one list is longer than the other, they are not equal
        return caller == null && originalArgs == null;
    }
    
    // Helper function to check if types are equal or one is an ancestor of the other
    private static boolean areTypesCompatible(TYPE sent, TYPE original) {
        if (sent == null && original == null) {
            return true;
        }
        if (sent == null || original == null) {
            return false;
        }
        if (sent.name.equals(original.name)) {
            return true;
        }
        if (sent instanceof TYPE_CLASS && original instanceof TYPE_CLASS) {
            // Check if type2 is an ancestor of type1
            return ((TYPE_CLASS) original).isAncestorOf((TYPE_CLASS) sent);
        }

        if( TYPE_NIL.canAssignNil(original) && sent instanceof TYPE_NIL){
            return true;
        }

        return false;
    }
    

	public TYPE search(String nameParam) {
		TYPE_LIST current = this; // Start from the current list
        while (current != null) {
            if (current.head != null && current.head.name.equals(nameParam)) {
                return current.head; // Return the matching type
            }
            current = current.tail; // Move to the next element in the list
        }
        return null; // If not found, return null
	}
}

