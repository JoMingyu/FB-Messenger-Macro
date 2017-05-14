from flask import request
from flask_restful import Resource
from fb import client
import hashlib
import os


class Account(Resource):
    def post(self):
        id = request.form['id']
        pw = request.form['pw']

        session = client.login(id, pw)

        if session:
            h = hashlib.sha256()
            ip = request.remote_addr
            h.update(ip.encode('utf-8'))
            session.saveSession(h.hexdigest())
            return '', 201
        else:
            return '', 204

    def get(self):
        ip = request.remote_addr
        h = hashlib.sha256()
        h.update(ip.encode('utf-8'))
        if os.path.isfile(h.hexdigest()):
            return '', 200
        else:
            return '', 204

    def delete(self):
        # logout
        ip = request.remote_addr
        h = hashlib.sha256()
        h.update(ip.encode('utf-8'))
        os.remove(h.hexdigest())


class Friend(Resource):
    def get(self):
        friend_name = request.args.get('friend_name')
        ip = request.remote_addr
        # Get informations from client

        h = hashlib.sha256()
        h.update(ip.encode('utf-8'))
        session_file_name = h.hexdigest()
        session = client.login_by_session(session_file_name)
        # login by session

        friend_info_list = []
        friends = session.getUsers(friend_name)
        for friend in friends:
            friend_info = session.getUserInfo(friend.uid)
            friend_object = {
                'name': friend_info['name'],
                'alternate_name': friend_info['alternateName'],
                'is_friend': friend_info['is_friend'],
                'type': friend_info['type'],
                'gender': friend_info['gender'],
                'uri': friend_info['uri']
            }
            friend_info_list.append(friend_object)

        if len(friend_info_list) == 0:
            return '', 204
        else:
            return friend_info_list, 200
