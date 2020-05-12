import { createStore, combineReducers, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import todos from './reducer';
import sagas from './sagas';


const sagaMiddleware = createSagaMiddleware();

const store = createStore(
    combineReducers({
        todos,
    }),
    applyMiddleware(sagaMiddleware)
);


sagaMiddleware.run(sagas);

export default store;