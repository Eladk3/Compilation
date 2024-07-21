package TYPES;

public class TYPE_FUNCTION extends TYPE
{
	/***********************************/
	/* The return type of the function */
	/***********************************/
	public TYPE returnType;

	/*************************/
	/* types of input params */
	/*************************/
	public TYPE_LIST params;
	
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_FUNCTION(TYPE returnType,String name,TYPE_LIST params)
	{
		this.name = name;
		this.returnType = returnType;
		this.params = params;
	}

	public boolean equalsSignature(TYPE_FUNCTION other) {
        // Compare return types
		System.out.println("return type of this "+ this.returnType.name);
		System.out.println("return type of other "+ other.returnType.name);

        if (!this.returnType.equals(other.returnType)) {
            return false; // Return types don't match
        }

        // Compare parameter types
        TYPE_LIST thisParams = this.params;
        TYPE_LIST otherParams = other.params;
        while (thisParams != null && otherParams != null) {
            // Check if parameter types match
            System.out.println("type of thisParams.head is: "+ thisParams.head.name);
            System.out.println("type of otherParams.head is: "+ otherParams.head.name);

            TYPE t1 = thisParams.head.getTypeOrTypeClassVarDec();
            TYPE t2 = otherParams.head.getTypeOrTypeClassVarDec();

            if (t1 != null && t2 != null && !(t1.equals(t2))) {
                return false; // Parameter types don't match
            }
            // Move to the next parameter
            thisParams = thisParams.tail;
            otherParams = otherParams.tail;
        }

        // If one list is longer than the other, signatures don't match
        if (thisParams != null || otherParams != null) {
            return false; // Parameter count doesn't match
        }

        return true; // Signatures match
    }
}
