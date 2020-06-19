import {createStore, Store} from 'redux';
import {RepositoriesState} from './ducks/repositories/types';

import rootReducers from './ducks/rootReducers';

export interface ApplicationState {
  repositories: RepositoriesState
}

const store: Store<ApplicationState> = createStore(rootReducers);

export default store;