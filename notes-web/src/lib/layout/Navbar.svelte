<script lang="ts">
    import {Navbar, NavBrand, Input} from 'flowbite-svelte'
    import {SearchOutline} from 'flowbite-svelte-icons'
    import Controls from "$lib/editor/controls/Controls.svelte";
</script>

<div class="navbar">
    <Navbar class="bg-transparent">
        <NavBrand href="/">
        <span class="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
            Notes4s ✍️
        </span>
        </NavBrand>
        <Controls/>
        <div class="flex">
            <div class="hidden relative md:block">
                <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
                    <SearchOutline size="sm" strokeWidth="1"/>
                </div>
                <Input id="search-navbar" size="sm" class="pl-10" placeholder="Search..."/>
            </div>
        </div>
    </Navbar>
</div>

<style lang="postcss">
    .navbar {
        @apply z-20 border-b top-0 left-0 right-0 backdrop-filter backdrop-blur-3xl sticky;
    }
</style>
