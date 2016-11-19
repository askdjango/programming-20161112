
webtoon_list = [{
    "title": "고수",
    "author": "문정후",
    "pageUrl": "http://comic.naver.com/webtoon/list.nhn?titleId=662774",
    "profileImageUrl": "http://thumb.comic.naver.net/webtoon/662774/thumbnail/title_thumbnail_20151002175819_t125x101.jpg",
}, {
    "title": "마음의 소리",
    "author": "조석",
    "pageUrl": "http://comic.naver.com/webtoon/list.nhn?titleId=20853",
    "profileImageUrl": "http://thumb.comic.naver.net/webtoon/20853/thumbnail/thumbnail_title_20853_125x101.gif",
}, {
    "title": "노블레스",
    "author": "손재호/이광수",
    "pageUrl": "http://comic.naver.com/webtoon/list.nhn?titleId=25455",
    "profileImageUrl": "http://thumb.comic.naver.net/webtoon/25455/thumbnail/title_thumbnail_20100614120245_t125x101.jpg",
}, {
    "title": "신의탑",
    "author": "SIU",
    "pageUrl": "http://comic.naver.com/webtoon/list.nhn?titleId=183559",
    "profileImageUrl": "http://thumb.comic.naver.net/webtoon/183559/thumbnail/title_thumbnail_20160516123017_t125x101.jpg",
}]

import json
json_string = json.dumps(webtoon_list, ensure_ascii=False, indent=4)

print("created!")
open('output.json', 'w').write(json_string)

