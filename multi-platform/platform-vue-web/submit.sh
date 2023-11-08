#!/bin/bash

submit(){
  echo '>>>>>>>>>>>>>>>>>>>>>>>>开始提交代码<<<<<<<<<<<<<<<<<<<<<<<<'
  git add .
  git config --global push.default "current"
  git commit -m '提交代码'
  git push
}

submit
