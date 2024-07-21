package TYPES;

public class TYPE_CLASS extends TYPE
{
	/*********************************************************************/
	/* If this class does not extend a father class this should be null  */
	/*********************************************************************/
	public TYPE_CLASS father;

	/**************************************************/
	/* Gather up all data members in one place        */
	/* Note that data members coming from the AST are */
	/* packed together with the class methods         */
	/**************************************************/
	public TYPE_LIST data_members;
	
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_CLASS(TYPE_CLASS father,String name,TYPE_LIST data_members)
	{
		this.name = name;
		this.father = father;
		this.data_members = data_members;
	}

    public boolean isAncestorOf(TYPE typeClass) {
		// Check if typeClass is the same as the current class
		if (!(typeClass instanceof TYPE_CLASS)) return false;
		if (this.equals(typeClass)) {
			return true;
		}
		
		// Traverse the inheritance hierarchy to check if typeClass is an ancestor
		TYPE_CLASS current = (TYPE_CLASS) typeClass;
		while (current.father != null) {
			current = current.father;
			if (current.equals(this)) {
				return true;
			}
		}
		
		// If typeClass is not found in the inheritance hierarchy, return false
		return false;
	}

	public TYPE_CLASS getAncestor()
	{
		return father;
	}

	public TYPE getDataMember(String fieldName) {
        // Search for the data member in the current class's data members
        TYPE_LIST currentDataMember = this.data_members;
        while (currentDataMember != null && currentDataMember.head != null) {
			System.out.println("fieldname"+fieldName);
            if (currentDataMember.head.name.equals(fieldName)) {
                return ((TYPE_CLASS_VAR_DEC)currentDataMember.head).t;
            }
            currentDataMember = currentDataMember.tail;
        }

        // If not found, check ancestor classes' data members
        TYPE_CLASS currentAncestor = this.father;
        while (currentAncestor != null) {
            currentDataMember = currentAncestor.data_members;
            while (currentDataMember != null) {
                if (currentDataMember.head.name.equals(fieldName)) {
                    return ((TYPE_CLASS_VAR_DEC)currentDataMember.head).t;

                }
                currentDataMember = currentDataMember.tail;
            }
            currentAncestor = currentAncestor.father; // Move to the next ancestor
        }

        // If not found in current class or its ancestors, return null
        return null;
    }

	public TYPE_FUNCTION getMethod(String methodName) {
        // Search for the method in the current class's methods
        TYPE_LIST currentMethod = this.data_members;
        while (currentMethod != null) {
            if (currentMethod.head.name.equals(methodName) && ((TYPE_CLASS_VAR_DEC)currentMethod.head).t instanceof TYPE_FUNCTION) {
                return (TYPE_FUNCTION)((TYPE_CLASS_VAR_DEC)currentMethod.head).t;
            }
            currentMethod = currentMethod.tail;
        }

        // If not found, check ancestor classes' methods
        TYPE_CLASS currentAncestor = this.father;
        if (currentAncestor != null) {
            return currentAncestor.getMethod(methodName);
        }

        // If not found in current class or its ancestors, return null
        return null;
    }
	
}
