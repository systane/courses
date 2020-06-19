import React, { Component } from 'react';
import { connect } from 'react-redux';
import {ApplicationState} from '../store'
import {Repository} from '../store/ducks/repositories/types'


interface StateProps {
  repositories: Repository[]
}

interface DispatchProps {

}

interface OwnProps {

}

type Props = StateProps & DispatchProps & OwnProps


 class RepositoryList extends Component {
  componentDidMount(){}

  render() {

    const { repositories } = this.props;
   
    return (
      <ul>
        {repositories: state.}
      </ul>
    )
  }
}

const mapStateToProps = (state: ApplicationState) => ({
  repositories: state.repositories.data,
});

export default connect(mapStateToProps)(RepositoryList);