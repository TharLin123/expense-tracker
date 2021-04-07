const contextReducer = (state, action) => {
    let transaction;

    switch (action.type) {
        case "DELETE_TRANSACTION":
            transaction = state.filter( t => t.id !== action.payload );
            return transaction;

        case "ADD_TRANSACTION":
            transaction = [action.payload,...state];
            return transaction;
        default:
            return state;
    }
}

export default contextReducer;
