/*
 *    Copyright (c) 2015-2016, EMC Corporation
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.emc.metalnx.services.interfaces;

import com.emc.metalnx.core.domain.entity.DataGridUser;
import com.emc.metalnx.core.domain.exceptions.DataGridConnectionRefusedException;
import com.emc.metalnx.core.domain.exceptions.DataGridException;
import com.emc.metalnx.service.utils.DataGridFileForUpload;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileOperationService {

    /**
     * Copy a file or collection between two locations in the data grid.
     *
     * @param sourcePath
     * @param targetPath
     * @return True, if file or collection was moved. False, otherwise.
     * @throws DataGridConnectionRefusedException
     */
    boolean copy(String sourcePath, String targetPath) throws DataGridConnectionRefusedException;

    /**
     * Copy a set of files or collections between two locations in the data grid.
     *
     * @param sourcePaths
     *            list of paths to be copied
     * @param targetPath
     *            path where the files/collections will be copied
     * @return True, if all files or collections were moved. False, otherwise.
     * @throws DataGridConnectionRefusedException
     */
    boolean copy(List<String> sourcePaths, String targetPath) throws DataGridConnectionRefusedException;

    /**
     * Delete a file or collection in iRODS
     *
     * @param path
     *            path to the collection or data object to be deleted
     * @param force
     *            delete collection/data object with force flag set
     * @throws DataGridConnectionRefusedException
     */
    boolean deleteItem(String path, boolean force) throws DataGridConnectionRefusedException;

    /**
     * Delete a collection in iRODS
     *
     * @param collectionPath
     *            path to the collection to be deleted
     * @param forceFlag
     *            when set to true, force delete an object (-f). When set to false, delete object no
     *            with
     *            force
     * @throws DataGridException
     */
    boolean deleteCollection(String collectionPath, boolean forceFlag) throws DataGridException;

    /**
     * Delete a data object in iRODS
     *
     * @param dataObjectPath
     *            path to the data object that will de removed
     * @param forceFlag
     *            when set to true, force delete an object (-f). When set to false, delete object no
     *            with
     *            force
     * @throws DataGridException
     */
    boolean deleteDataObject(String dataObjectPath, boolean forceFlag) throws DataGridException;

    /**
     * Delete a replica of a data object
     *
     * @param path
     *            path to the parent of the data object to be deleted
     * @param replicaNumber
     *            number of the replica that is going to be deleted
     * @param inAdminMode
     *          run the command as admin (-M option)
     * @return true if the operation was successfull and false otherwise
     * @throws DataGridConnectionRefusedException
     */
    boolean deleteReplica(String path, String fileName, int replicaNumber, boolean inAdminMode) throws DataGridConnectionRefusedException;

    /**
     * Download a file or collection from the data grid.
     *
     * @param path
     *            file to download
     * @param httpResponse
     *            response to an http request
     * @param removeTempCollection
     *            flag when set to true tells Metalnx to remove temporary collections and tar files
     *            created for downloading. When set to false, just puts the file into the HTTP
     *            response
     * @return True, if file or collection was downloaded. False, otherwise.
     * @throws DataGridException
     * @throws IOException
     */
    boolean download(String path, HttpServletResponse httpResponse, boolean removeTempCollection) throws DataGridException, IOException;

    /**
     * Removes all items existing in the trash folder of a given user.
     *
     * @param user
     *            user who will get the trash cleaned
     * @param currentPath
     *            path from which the trash path will be extracted
     * @return True, if all trash items were removed. False, otherwise.
     * @throws DataGridConnectionRefusedException
     */
    boolean emptyTrash(DataGridUser user, String currentPath) throws DataGridConnectionRefusedException;

    /**
     * Move a file or collection between two locations in the data grid.
     *
     * @param sourcePath
     * @param targetPath
     * @return True, if file or collection was moved. False, otherwise.
     * @throws DataGridException
     */
    boolean move(String sourcePath, String targetPath) throws DataGridException;

    /**
     * Replicates a file into another resource.
     *
     * @param path
     *            path to the file to be replicated
     * @param targetResource
     *            resource where the replica will be stored
     * @param inAdminMode
     *          replicate object in admin mode (-M option)
     * @return true, if file was successfully replicated
     *         false, otherwise.
     * @throws DataGridConnectionRefusedException
     */
    boolean replicateDataObject(String path, String targetResource, boolean inAdminMode)
            throws DataGridConnectionRefusedException;

    /**
     * Transfer a file to the data grid.
     *
     * @param fileForUpload
     *            file that will be transferred
     * @return True, if file or collection was uploaded. False, otherwise.
     * @throws DataGridException
     */
    boolean transferFileToDataGrid(DataGridFileForUpload fileForUpload) throws DataGridException;

}
