import * as uuid from 'uuid';

import { Group } from '../models/Group';
import { GroupAccess } from 'src/dataLayer/groupsAccess';
import { CreateGroupRequest } from '../requests/CreateGroupRequest';
import { getUserId } from '@libs/util';

const groupAccess = new GroupAccess();

export async function getAllGroups(): Promise<Group[]> {
    return groupAccess.getAllGroups();
};

export async function createGroup(
    createGroupRequest: CreateGroupRequest,
    jwtToken: string
): Promise<Group> {
    const itemId = uuid.v4();
    const userId = getUserId(jwtToken);

    return await groupAccess.createGroup({
        id: itemId,
        userId: userId,
        name: createGroupRequest.name,
        description: createGroupRequest.description,
        timestamp: new Date().toISOString()
    });
};