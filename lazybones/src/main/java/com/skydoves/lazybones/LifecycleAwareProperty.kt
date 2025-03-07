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

@file:Suppress("unused")

package com.skydoves.lazybones

import androidx.lifecycle.LifecycleOwner

/** creates a [LifecycleAwareProperty] by [LifecycleAwareProperty.Builder] using dsl. */
@JvmSynthetic
@LazybonesWithNoInlines
inline fun <T : Any> LifecycleAwareProperty<T>.observe(
  block: LifecycleAwareProperty.Builder<T>.() -> Unit
): LifecycleAwareProperty<T> =
  LifecycleAwareProperty.Builder(this.lifecycleOwner, this.value).apply(block).build()

/** LifecycleAwareProperty is an observer for notifying lifecycle is changed. */
class LifecycleAwareProperty<T : Any> constructor(
  val lifecycleOwner: LifecycleOwner,
  var value: T
) {

  /** observes on the [On] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOn(on: On, receiver: T.() -> Unit) = apply {
    addLifecycleObserver(on, receiver)
  }

  /** observes on the [On.CREATE] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnCreate(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.CREATE, receiver)
  }

  /** observes on the [On.START] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnStart(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.START, receiver)
  }

  /** observes on the [On.RESUME] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnResume(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.RESUME, receiver)
  }

  /** observes on the [On.PAUSE] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnPause(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.PAUSE, receiver)
  }

  /** observes on the [On.STOP] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnStop(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.STOP, receiver)
  }

  /** observes on the [On.DESTROY] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnDestroy(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.DESTROY, receiver)
  }

  /** observes on the [On.ANY] lifecycle state. */
  @JvmSynthetic
  @LazybonesWithNoInlines
  fun observeOnAny(receiver: T.() -> Unit) = apply {
    addLifecycleObserver(On.ANY, receiver)
  }

  /** adds a lifecycle observer based on [On] lifecycle state internally. */
  @JvmSynthetic
  private fun addLifecycleObserver(on: On, receiver: T.() -> Unit) {
    val observer = on.getOnLifecyclePropertyObserver(this.value)
    observer.registerLifecyclePropertyObserver { value -> receiver(value) }
    this.lifecycleOwner.lifecycle.addObserver(observer)
  }

  /** Builder class for creating [LifecycleAwareProperty]. */
  class Builder<T : Any>(lifecycleOwner: LifecycleOwner, value: T) {
    private val lifecycleAwareProperty = LifecycleAwareProperty(lifecycleOwner, value)

    /** observes on the [On] lifecycle state. */
    @JvmSynthetic
    @LazybonesWithNoInlines
    fun on(on: On, receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(on, receiver)
    }

    /** observes on the [On.CREATE] lifecycle state. */
    @JvmSynthetic
    @LazybonesWithNoInlines
    fun onCreate(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.CREATE, receiver)
    }

    /** observes on the [On.START] lifecycle state. */
    @JvmSynthetic
    @LazybonesWithNoInlines
    fun onStart(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.START, receiver)
    }

    /** observes on the [On.RESUME] lifecycle state. */
    @JvmSynthetic
    @LazybonesWithNoInlines
    fun onResume(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.RESUME, receiver)
    }

    /** observes on the [On.PAUSE] lifecycle state. */
    @LazybonesWithNoInlines
    @JvmSynthetic
    fun onPause(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.PAUSE, receiver)
    }

    /** observes on the [On.STOP] lifecycle state. */
    @LazybonesWithNoInlines
    @JvmSynthetic
    fun onStop(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.STOP, receiver)
    }

    /** observes on the [On.DESTROY] lifecycle state. */
    @LazybonesWithNoInlines
    @JvmSynthetic
    fun onDestroy(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.DESTROY, receiver)
    }

    /** observes on the [On.ANY] lifecycle state. */
    @LazybonesWithNoInlines
    @JvmSynthetic
    fun onAny(receiver: T.() -> Unit) = apply {
      this.lifecycleAwareProperty.addLifecycleObserver(On.ANY, receiver)
    }

    /** returns a new instance of [LifecycleAwareProperty]. */
    fun build() = this.lifecycleAwareProperty
  }
}
