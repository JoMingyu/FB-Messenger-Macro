from flask import request
from flask_restful import Resource
from fb import client
import hashlib
import os
import time


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
                'uid': friend.uid,
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


class Message(Resource):
    def post(self):
        ip = request.remote_addr
        # Get ip from client

        h = hashlib.sha256()
        h.update(ip.encode('utf-8'))
        session_file_name = h.hexdigest()
        session = client.login_by_session(session_file_name)
        # login by session

        uid = request.form['uid']
        message = request.form['message']
        send_type = request.form['send_type']
        interval = int(request.form['interval'])
        # default data

        if send_type == '1':
            # Infinite
            time_limit = int(request.form['time_limit'])
            start = time.time()
            while True:
                session.send(uid, message)
                time.sleep(float(interval))
                if time.time() - start > time_limit:
                    break

        elif send_type == 2:
            # Limited
            send_count = request.form['send_count']
            count = 0
            for i in range(send_count):
                session.send(uid, message)
                count += 1
                if count == send_count:
                    break
                time.sleep(float(interval))
