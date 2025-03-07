/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("OnLifecycle")
@file:JvmMultifileClass

package com.skydoves.lazybones

/** Defines an object that has an Android Lifecycle. */
enum class On {
  CREATE,
  START,
  RESUME,
  PAUSE,
  STOP,
  DESTROY,
  ANY
}

/** gets an [OnLifecycleObserver] from a instance of [On]. */
internal fun <T : Any> On.getOnLifecycleObserver(): OnLifecycleObserver<T> {
  return when (this) {
    On.CREATE -> OnCreateObserver()
    On.START -> OnStartObserver()
    On.RESUME -> OnResumeObserver()
    On.PAUSE -> OnPauseObserver()
    On.STOP -> OnStopObserver()
    On.DESTROY -> OnDestroyObserver()
    On.ANY -> OnAnyObserver()
  }
}

/** gets an [OnLifecyclePropertyObserver] from a instance of [On]. */
internal fun <T : Any> On.getOnLifecyclePropertyObserver(value: T): OnLifecyclePropertyObserver<T> {
  return when (this) {
    On.CREATE -> OnCreatePropertyObserver(value)
    On.START -> OnStartPropertyObserver(value)
    On.RESUME -> OnResumePropertyObserver(value)
    On.PAUSE -> OnPausePropertyObserver(value)
    On.STOP -> OnStopPropertyObserver(value)
    On.DESTROY -> OnDestroyPropertyObserver(value)
    On.ANY -> OnAnyPropertyObserver(value)
  }
}
